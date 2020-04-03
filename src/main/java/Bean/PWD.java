package Bean;

public class PWD {
    private String adminToken;
    private String oldPwd;
    private String newPwd;
    private String confirmPwd;

    public String getAdminToken() {
        return adminToken;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setAdminToken(String adminToken) {
        this.adminToken = adminToken;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

    @Override
    public String toString() {
        return "PWD{" +
                "adminToken='" + adminToken + '\'' +
                ", oldPwd='" + oldPwd + '\'' +
                ", newPwd='" + newPwd + '\'' +
                ", confirmPwd='" + confirmPwd + '\'' +
                '}';
    }
}
