import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.course")   //表示扫描哪个路径下的文件
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
