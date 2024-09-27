package model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public record UserData(String name,
                       String email,
                       String password,
                       String token) implements Map<String, String> {

    public UserData() {
        this("", "", "", "");
    }

    public UserData withName(String name) {
        return new UserData(name, this.email, this.password, this.token);
    }

    public UserData withEmail(String email) {
        return new UserData(this.name, email, this.password, this.token);
    }

    public UserData withPassword(String password) {
        return new UserData(this.name, this.email, password, this.token);
    }

    public UserData withToken(String token) {
        return new UserData(this.name, this.email, this.password, token);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }
    @NotNull
    @Override
    public String get(Object key) {
        return "";
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        return "";
    }

    @Override
    public String remove(Object key) {
        return "";
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {

    }

    @Override
    public void clear() {

    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return Set.of();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return List.of();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return Set.of();
    }


}
