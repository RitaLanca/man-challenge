package com.man.fota.domain.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class Configuration {

    @Id
    private String code;

    public Configuration() {
    }

    public Configuration(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Configuration that = (Configuration) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    public static final class ConfigurationBuilder {
        private Configuration configuration;

        private ConfigurationBuilder() {
            configuration = new Configuration();
        }

        public static ConfigurationBuilder builder() {
            return new ConfigurationBuilder();
        }

        public ConfigurationBuilder code(String code) {
            configuration.setCode(code);
            return this;
        }

        public Configuration build() {
            return configuration;
        }
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "code='" + code + '\'' +
                '}';
    }
}
