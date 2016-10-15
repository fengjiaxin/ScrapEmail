import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScrapTest {

	public static void main(String[] args) throws IOException {
		String des = "http://blog.sina.com.cn/s/blog_147f99d5d0102vmyw.html";
		List<String> list = getMailsByWeb(des);
		for(String mail:list)
			System.out.println(mail);

	}

	private static List<String> getMailsByWeb(String str) throws IOException {
		//1.读取源文件
		URL url = new URL(str);
		BufferedReader bufin = new BufferedReader(new InputStreamReader(url.openStream()));
		//2.对读取的数据进行规则的匹配，从中获取符合规则的数据
		String mail_regex ="\\w+@\\w+(\\.\\w+)+";
		List<String> list = new ArrayList<String>();
		Pattern p = Pattern.compile(mail_regex);
		String line = null;
		while((line = bufin.readLine())!=null)
		{
			Matcher m = p.matcher(line);
			while(m.find())
			//3.将符合规则的数据存储到集合作用
				list.add(m.group());
		}
		
		return list;
	}

}
