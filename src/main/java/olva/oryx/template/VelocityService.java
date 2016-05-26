package olva.oryx.template;

import olva.oryx.util.Constants;
import olva.oryx.util.UtilString;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.NumberTool;

import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

/**
 * 
 * @author clarico
 *
 */
public class VelocityService {

	/**
	 * 
	 * @author clarico
	 * @since 22 de feb. de 2016
	 * @param template
	 * @param data
	 * @return
	 */
	public StringWriter getDataFromTemplate(String template, Map<String, String> data) {

		VelocityEngine ve = new VelocityEngine();

		Properties props = new Properties();

		props.put("file.resource.loader.path", Constants.ORYX_TEMPLATE_FOLDER);
		ve.init(props);
		Template t = ve.getTemplate(template+".vm");

		VelocityContext context = new VelocityContext();
		
		context.put("util", StringUtils.class);
		context.put("numberTool", new NumberTool());
		context.put("utilOlva", UtilString.class);
		context.put("object", data);

		StringWriter writer = new StringWriter();
		t.merge(context, writer);

		return writer;

	}



}
