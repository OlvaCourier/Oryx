package olva.oryx.system;

import com.jcabi.log.Logger;
import olva.oryx.service.Init;
import olva.oryx.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Initial Behavior
 *
 *
 *
 * @author Carlos D Larico (clarico@olva.com.pe)
 */
public final class InitBehavior {



    private static transient List<String> arguments;
    private static transient List<String> doubleArguments;
    private static transient List<Option> options;

    private final static transient String P_PORT = "-p";

    // FIXME: 27/05/2016  remove this method
    private static void showAll(){
        for (String argument : arguments) {
            Logger.info(InitBehavior.class, "Arguments -> %s", argument);
        }

        for (String doubleArgument : doubleArguments) {
            Logger.info(InitBehavior.class, "doubleArgument -> %s", doubleArgument);
        }

        for (Option option : options) {
            Logger.info(InitBehavior.class, "option -> %s", option.toString());
        }
    }

    /**
     * Run configs
     * @param args app arguments
     */
    public static void run(String[] args) throws IllegalArgumentException{
        getOptions(args);
        //Logger.info(InitBehavior.class, "Lista argumentos %s", options.get(0).getVal());
        showAll();
        //process();
    }

    /**
     * Process arguments
     */
    private static void process() {
        options.stream().filter(option -> option.getOpt().equals(P_PORT)).forEach(
            opt -> setPort(opt.getVal())
        );
    }

    /**
     * Set port
     * @param port port
     */
    private static void setPort(String port){
        try {
            Logger.info(InitBehavior.class, "Port: %s", port);
            Constants.PORT =  Integer.parseInt(port);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Not a valid argument for Port: "+port);
        }
    }


    /**
     * Get arguments and options
     */
    private static void getOptions(String[] args){

        arguments = new ArrayList<>();
        options = new ArrayList<>();
        doubleArguments = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            switch (args[i].charAt(0)){
                case '-':
                    if (args[i].length() < 2){
                        throw new IllegalArgumentException("Not a valid argument: "+args[i]);
                    }
                    if (args[i].charAt(1) == '-') {
                        if (args[i].length() < 3){
                            throw new IllegalArgumentException("Not a valid argument: "+args[i]);
                        }
                        // --opt
                        doubleArguments.add(args[i].substring(2, args[i].length()));
                    } else {
                        if (args.length-1 == i)
                            throw new IllegalArgumentException("Expected arg after: "+args[i]);
                        // -opt
                        options.add(new Option(args[i], args[i+1]));
                        i++;
                    }
                    break;
                default:
                    arguments.add(args[i]);
                    break;
            }
        }
    }


}
