package httl.test;

import httl.test.model.Book;
import httl.test.model.User;
import httl.test.performance.Case;
import httl.test.performance.Counter;
import httl.test.performance.FreemarkerCase;
import httl.test.performance.HttlCase;
import httl.test.performance.JavaCase;
import httl.test.performance.Smarty4jCase;
import httl.test.performance.VelocityCase;
import httl.util.IgnoredWriter;

import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.junit.Test;

public class ShowTest {

    @Test
    public void testShow() throws Exception {
        int size = 2;
        int times = 1;
        Random random = new Random();
        Book[] books = new Book[size];
        for (int i = 0; i < size; i ++) {
            books[i] = new Book(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), new Date(), random.nextInt(100) + 10, random.nextInt(60) + 30);
        }
        Map<String, Object> context = new HashMap<String, Object>();
        context.put("user", new User("liangfei", "admin", "Y"));
        context.put("books", books);
        Case[] cases = new Case[] {new HttlCase(), new FreemarkerCase(), new VelocityCase(), new Smarty4jCase(), new JavaCase()};
        for (int i = 0; i < cases.length; i ++) {
            System.out.println("=============");
            System.out.println(cases[i].getClass().getSimpleName().replace("Case", ""));
            System.out.println("=============");
            Case c = cases[i % cases.length];
            Counter counter = new Counter();
            StringWriter writer = new StringWriter();
            c.count("books", new HashMap<String, Object>(context), writer, new IgnoredWriter(), times, counter);
            System.out.println(writer.getBuffer().toString());
        }
        System.out.println("=============");
    }
    
    public static void main(String[] args) throws Exception {
        new ShowTest().testShow();
    }
    
}