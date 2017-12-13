package co.bk.javabasics.main;

import static java.lang.String.format;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scratch {




    public static void main(String[] args) {


        try {

//            System.out.println("hello...");
//
//            Double i=170.3316;
//            //long j=Math.round(i*100);
//
//
//            BigDecimal result = new BigDecimal(i.toString()).multiply(new BigDecimal(("100"))).stripTrailingZeros();
//            System.out.println(result);

            LocalDate arrDate = LocalDate.now();
            //LocalDate arrDate = null;

            LocalDate deptDate = LocalDate.now().plus(1, ChronoUnit.DAYS);
            //LocalDate deptDate = null;




//            BiPredicate<LocalDate, LocalDate> areDatesValid = (arrivalDate, departureDate)
//                    -> arrivalDate != null && departureDate != null && arrivalDate.isBefore(departureDate) == true;

            BiPredicate<LocalDate, LocalDate> areDatesValid = (arrivalDate, departureDate)
                    -> arrivalDate.isBefore(departureDate) == true;

            boolean datesValid = true;
            if (!areDatesValid.test(arrDate, deptDate)) {
                datesValid = false;
                //throw new Exception("dates not valid");
                //throw new StrikePriceServiceException(StrikePriceServiceException.ErrorCode.INVALID_DATA);
            }
            System.out.print("dates valid:" + datesValid);

        } catch (Exception e) {
            //System.out.println();
            e.printStackTrace();
        }



        //String[] dateElements = "05.10.2017".split("[.]");


//        Date date = Date.parse("dd.MM.yyyy", dateS);
//
//        //Date date = new Date(Integer.parseInt(dateElements[2]), Integer.parseInt(dateElements[1]), Integer.parseInt(dateElements[0]));
//
//
//        // JAVA 8
//        LocalDate date = LocalDate.of(Integer.parseInt(dateElements[2]), Integer.parseInt(dateElements[1]), Integer.parseInt(dateElements[0]));
//
//        System.out.println("localDate " + date.toString() );
//
//        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        String dateFormatted = formatter.format(date);
//        System.out.println("dateFormatted " + dateFormatted );
//
//
//
//        String dateIs =  LocalDate.now().plusMonths(3).plusDays(1).toString();

//        // search for "<div class="title">
//    <h2>Meliá</h2>
//</div>
        try {
            //String input = "1 fish 2 fish red fish blue fish";
            //Scanner s = new Scanner(input).useDelimiter("\\s*fish\\s*");
//            Scanner s = new Scanner(new FileInputStream(new File("/dmp/hotelpage.txt")))
//                    .useDelimiter("\\s*\"<title>(.+?)</title>\"\\s*");

//            Scanner s = new Scanner(new FileInputStream(new File("/dmp/hotelpage.txt")));
//                    //.useDelimiter("\\s*\"<title>(.+?)</title>\"\\s*");
//
//
//
//
//            //"<title>(.+?)</title>"
//            String text = null;
//            while(s.hasNext()) {
//                //System.out.println(s.nextLine());
//                text = s.findInLine("<title>(.+?)</title>");
//                if (text != null) {
//                    System.out.println("text is: " + text);
//                    break;
//                } else {
//                    s.nextLine();
//                }
//            }
//
//            List<String> content = extractHtmlTagContent("title", text);
//            System.out.println("conent: " + content.get(0));
//            s.close();

            //System.out.println("length is: " + htmlPage.length());


//            ObjectMapper mapper = new ObjectMapper();
//            Worker emp = Worker.builder().workerName("Deendaya").salary(100).build();
//            System.out.println("worker is: " + emp.toString());
//            String jsonInString = mapper.writeValueAsString(emp);
//            System.out.println("jsonInString: " + jsonInString);
//            Worker worker = mapper.readValue(jsonInString, Worker.class);
//            System.out.println("worker is: " + worker.toString());

        } catch (Exception e) {
            System.out.println("exception: " + e.getMessage());
        }

    }


//    public String getTenantIdByNestedIndex(int index) {
//        String tenantId = null;
//        if (!"".equalsIgnoreCase(this.getTenantId())) {
//            if (!this.getTenantId().endsWith(".") && !this.getTenantId().startsWith(".") && this.getTenantId().contains(".")) {
//                // tenant has sub-tenants
//                String[] tenantHierarchy = this.getTenantId().split("\\.");
//                if (index < tenantHierarchy.length) {
//                    tenantId = tenantHierarchy[index];
//                }
//            } else {
//                // tenant has no sub-tenants
//                tenantId = this.getTenantId();
//            }
//        }
//        return tenantId;
//    }

    protected static List<String> extractHtmlTagContent(final String tagNameNoBracket, final String str) {

        final String regex = format("<%s>(.+?)</%s>", tagNameNoBracket, tagNameNoBracket);
        final Pattern PATTERN_TAG_REGEX = Pattern.compile(regex);

        final List<String> tagValues = new ArrayList<String>();
        final Matcher matcher = PATTERN_TAG_REGEX.matcher(str);
        while (matcher.find()) {
            tagValues.add(matcher.group(1));
        }
        return tagValues;
    }


    protected static String departureDate(String stayDuration) {

        if (stayDuration != null && "longStay".equalsIgnoreCase(stayDuration)) {
            return LocalDate.now().plusMonths(3).plusDays(3).toString();
        } else {
            return LocalDate.now().plusMonths(3).plusDays(1).toString();
        }
    }

}
