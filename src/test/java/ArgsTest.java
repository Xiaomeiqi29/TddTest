import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArgsTest {
    @Test
    public void should_set_boolean_option_to_true_if_flag_is_present() {
        BooleanOption option = Args.parse(BooleanOption.class, "-l");
        assertTrue(option.logging);
    }

    @Test
    public void should_set_boolean_option_to_false_if_flag_not_present() {
        BooleanOption option = Args.parse(BooleanOption.class);
        assertFalse(option.logging);
    }

    static record BooleanOption(@Option("l") boolean logging) {
    }

    @Test
    public void should_parse_int_as_option_value() {
        IntOption option = Args.parse(IntOption.class, "-p", "8080");
        assertEquals(8080, option.port);
    }

    static record IntOption(@Option("p") int port) {
    }

    @Test
    public void should_get_string_as_option_value() {
        StringOption option = Args.parse(StringOption.class, "-d", "/user/logs");
        assertEquals("/user/logs", option.directory);
    }

    static record StringOption(@Option("d") String directory) {
    }

    @Test
    public void should_parse_multi_options() {
        MultiOptions options = Args.parse(MultiOptions.class, "-l", "-p", "8080", "-d", "/user/logs");
        assertTrue(options.logging);
        assertEquals(8080, options.port);
        assertEquals("/user/logs", options.directory);
    }

    static record MultiOptions(@Option("l") boolean logging, @Option("p") int port, @Option("d") String directory) {
    }
}
