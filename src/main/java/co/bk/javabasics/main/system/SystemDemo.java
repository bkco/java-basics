package co.bk.javabasics.main.system;

import java.lang.*;

public class SystemDemo {

    public static void main(String[] args) throws Exception {

        /*
         * Debian/Ubuntu need to set MAVEN_REPO in /etc/profile.d/mavenRepo.sh file
         * Variables in .bashrc only picked up by shell and not apps like intellij.
         */
        System.out.println("System.getenv('MAVEN_REPO') = ");
        System.out.println(System.getenv("MAVEN_REPO"));

        // gets the value of the specified environment variable "TEMP"
        System.out.print("System.getenv('PATH') = ");
        System.out.println(System.getenv("PATH"));

        // gets the value of the specified environment variable "USERNAME"
        System.out.print("System.getenv('USERNAME') = ");
        System.out.println(System.getenv("USERNAME"));

        System.out.print("System.getenv('USER') = ");
        System.out.println(System.getenv("USER"));

        System.out.print("System.getenv('user.home') = ");
        System.out.println(System.getenv("user.home"));

        System.out.print("System.getenv('USER.HOME') = ");
        System.out.println(System.getenv("USER.HOME"));


    }
}