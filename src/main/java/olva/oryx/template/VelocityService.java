package olva.oryx.template;

import com.jcabi.aspects.Cacheable;
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

    private final transient Properties props;

    private VelocityEngine ve;

    /**
     * ctor
     * @param props properties
     */
    public VelocityService(Properties props) {
        this.props = props;
    }

    /**
     * ctor
     */
    public VelocityService() {
        this(VelocityService.getDefautls());
    }

    /**
     * load Engine
     */
    private void loadEngine(){
        ve = new VelocityEngine();
		ve.init(props);
	}


	public StringWriter transform(String template, Map<String, String> data) {

        loadEngine();

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


    @Cacheable
    private static Properties getDefautls(){
        Properties $props = new Properties();
        $props.put("file.resource.loader.path", Constants.ORYX_TEMPLATE_FOLDER);
        return $props;
    }

}
