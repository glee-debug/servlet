package cn.tedu.utils;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.IOException;

public class ThUtils {
	private static TemplateEngine te;
	static {
		//创建模板引擎对象
		te = new TemplateEngine();
		//创建解析器  该解析器会自动查找src/main/resources目录下的模板页面
		ClassLoaderTemplateResolver r = 
				new ClassLoaderTemplateResolver();
		//设置字符集
		r.setCharacterEncoding("UTF-8");
		//让解析器和模板引擎关联
		te.setTemplateResolver(r);
	}
	//Context导包 org.thymeleaf.Context
	public static String print(String fileName,Context context) throws IOException {
		//将页面和数据整合到一起的到一个新的html字符串
		String html = te.process(fileName, context);
		return html;
	}
}




