module stockscreener.restclient {
    requires transitive stockscreener.model;
    requires jackson.annotations;
    requires spring.web;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jdk8;
    requires com.fasterxml.jackson.module.paramnames;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires java.sql;
    exports com.rasto.stockscreener.restclient;
    exports com.rasto.stockscreener.restclient.core.request;
    exports com.rasto.stockscreener.restclient.core.response;
}
