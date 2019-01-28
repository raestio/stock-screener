module stockscreener.restclient {
    requires stockscreener.model;
    requires jackson.annotations;
    requires spring.web;
    exports com.rasto.stockscreener.restclient;
}
