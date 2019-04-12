package com.fred4jupiter.keycloak.keycloakdemo.web;

import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Component
public class BuildInfoUtil {

    private final BuildProperties buildProperties;

    public BuildInfoUtil(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    public String getAppVersion() {
        return this.buildProperties.getVersion();
    }

    public String getBuildTimestamp() {
        Instant time = this.buildProperties.getTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.getDefault()).withZone(ZoneId.systemDefault());
        return formatter.format(time);
    }
}

