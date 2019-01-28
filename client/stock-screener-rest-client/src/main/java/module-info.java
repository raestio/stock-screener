module stockscreener.restclient {
    requires stockscreener.model;
    requires jackson.annotations;
    requires spring.web;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jdk8;
    requires com.fasterxml.jackson.module.paramnames;
    requires com.fasterxml.jackson.datatype.jsr310;
    exports com.rasto.stockscreener.restclient;
}
