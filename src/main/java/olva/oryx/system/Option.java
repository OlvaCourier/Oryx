package olva.oryx.system;

import lombok.ToString;

/**
 * System Options.
 *
 * @author Carlos D Larico (clarico@kiwisoft.pe)
 */
@ToString
public final class Option {

    private final transient String opt;
    private final transient String val;

    /**
     * ctor
     * @param opt option
     * @param val value
     */
    public Option(String opt, String val) {
        this.opt = opt;
        this.val = val;
    }

    /**
     * get Option
     * @return option
     */
    public String getOpt() {
        return opt;
    }

    /**
     * get Value
     * @return value
     */
    public String getVal() {
        return val;
    }




}
