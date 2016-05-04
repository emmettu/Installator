package models.panels;

import models.InstallerModel;

import java.util.Optional;

/**
 * Created by eunderhi on 02/05/16.
 * Model for representing users desired Ldap settings
 */
public class LdapModel extends InstallerModel {

    private String name;
    private String url;
    private String dN;
    private String password;
    private String realmName;
    private String baseDN;
    private boolean recursive;
    private FilterType filterType;
    private String filter;
    private Optional<VaultModel> vault = Optional.empty();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getdN() {
        return dN;
    }

    public void setdN(String dN) {
        this.dN = dN;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealmName() {
        return realmName;
    }

    public void setRealmName(String realmName) {
        this.realmName = realmName;
    }

    public String getBaseDN() {
        return baseDN;
    }

    public void setBaseDN(String baseDN) {
        this.baseDN = baseDN;
    }

    public boolean isRecursive() {
        return recursive;
    }

    public void setRecursive(boolean recursive) {
        this.recursive = recursive;
    }

    public FilterType getFilterType() {
        return filterType;
    }

    public void setFilterType(FilterType filterType) {
        this.filterType = filterType;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public void setVaultModel(VaultModel vaultModel) {
        this.vault = Optional.of(vaultModel);
    }

    public void vaultPassword() {
        vault.ifPresent(v -> password = v.vaultPassword("ldap", name + ".password", password));
    }

    public enum FilterType {

        USERNAME(",username-attribute=\""),
        ADVANCED(",advanced-filter=\"");

        String command;
        FilterType(String command) {
            this.command = command;
        }

        public String getCommand() {
            return command;
        }

    }

}
