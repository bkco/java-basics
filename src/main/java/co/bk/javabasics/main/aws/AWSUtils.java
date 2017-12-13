package co.bk.javabasics.main.aws;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

/**
 * Amazon AWS utilities class.
 * <p>
 * Alternative to using Mule S3 connector to interact with S3. 
 * 
 * @author Brian Kelly
 */
public class AWSUtils {

	private static AmazonS3 s3;
	
	private static AWSCredentials credentials;
	
	/*
	 * Steps to download file from S3 server. Main method included for demo purposes.
	 * 
	 * AWS credentials need to be setup in the file "aws.properties" to connect to a real AWS instance.
	 */
	public static void main(String[] args) {
		
		String bucketName = "mybucket";
		String fileToDownload = "interesting_data.csv";
		String localDirectoryFileSavedIn = "/usr/downloads";
		
		try {
			if (doesFileExist(bucketName, fileToDownload)) {
				byte[] fileBytes = downloadFile(bucketName, fileToDownload);
				writeFileToDisk(fileBytes, localDirectoryFileSavedIn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method initialises an AmazonS3 client (used to connect to S3 service)
	 * 
	 * @return
	 */
	public synchronized static AmazonS3 getS3() {
		try {
			if (s3 == null) {
				credentials = getCredentials();
				s3 = new AmazonS3Client(credentials);
			}
			
		} catch (AmazonClientException ace) {
			throw new RuntimeException(ace);
		}
		return s3;
	}
	
	/**
	 * Method loads the keys for AWS.
	 * <p>
	 * Requires that "AwsCredentials.properties" is on the classpath and contains "accessKey" and "secretKey".
	 */
	private static AWSCredentials getCredentials() {
		final PropertiesCredentials credentials;
		
		try {
			final InputStream is = AWSUtils.class.getClassLoader().getResourceAsStream("aws.properties"); //From ClassLoader, all paths are "absolute" already		
			credentials = new PropertiesCredentials(is);
				
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return credentials;
	}
	
	/**
	 * Method downloads a file and returns it as a byte[]
	 * 
	 * @param bucket
	 * @param filePathForDownload
	 * @return
	 */
	public static byte[] downloadFile(String bucket, String filePathForDownload) {
		s3 = getS3();
		
		InputStreamReader reader = null;
		S3ObjectInputStream is = null;
		int totalBytesRead = 0;
		byte [] buffer;
		try {
			//Test local CSV read e.g C:\\myFile.csv
			//File fileReadMe = new File(filePathForDownload);
			//reader = new InputStreamReader(new FileInputStream(fileReadMe), "UTF-8");
			
			//S3 implementation
			S3Object s3object = s3.getObject(bucket, filePathForDownload);
			is = s3object.getObjectContent();
			reader = new InputStreamReader(is, "UTF-8");
			
			buffer = new byte[(int) s3object.getObjectMetadata().getContentLength()];
			
			int bytesRead = -1;
			
			while (true) {
				bytesRead = is.read(buffer, totalBytesRead, buffer.length - totalBytesRead);
				if (bytesRead == -1) {
					break;
				} else {
					totalBytesRead += bytesRead;
				}
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally{
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return buffer;
	}
	
	/**
	 * Save downloaded file (byte[]) to disk.
	 * 
	 * @param contentDownloadedFile
	 * @param directoryAndFile directory and file location to save to. For example: C:/newfile.txt
	 */
	public static void writeFileToDisk(byte[] contentDownloadedFile, String directoryAndFile) {
		
		FileOutputStream fop = null;
		File file;
		try {
			file = new File(directoryAndFile); //e.g C:/newfile.txt
			fop = new FileOutputStream(file);
			fop.write(contentDownloadedFile);
			fop.flush();
			fop.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * Method checks if a file with a specified name exists on an S3 server
	 * @param s3
	 * @param bucketName
	 * @param directoryAndFilePath - filename to check for
	 * @return
	 * @throws AmazonClientException
	 * @throws AmazonServiceException
	 */
	public static boolean doesFileExist(String bucketName, String directoryAndFilePath) throws AmazonClientException, AmazonServiceException {
		boolean bFileExists = true;
		try {
			getS3();
			ObjectMetadata objectMetadata = s3.getObjectMetadata(bucketName,  directoryAndFilePath);			
		} catch (AmazonS3Exception s3e) {
			if (s3e.getStatusCode() == 404) {
				bFileExists = false;
			} else {
				throw s3e;
			}
		}
		return bFileExists;
	}
	
	
	
}
