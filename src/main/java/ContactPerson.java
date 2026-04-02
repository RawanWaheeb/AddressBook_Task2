public class ContactPerson {


    private  int id;
    private String name;
    private String nickName;
    private  String address;
    private String homePhone;
    private String workPhone;
    private String cellPhone;
    private String email;
    private String birthday;
    private String webSite;
    private  String profession;


    public  ContactPerson(){}

    public  ContactPerson(int id,String name,String nickName, String address,String homePhone,String workPhone,String cellPhone,String email, String birthday,String webSite,String profession){
        this.id=id;
        this.name=name;
        this.nickName=nickName;
        this.address=address;
        this.homePhone=homePhone;
        this.workPhone=workPhone;
        this.cellPhone=cellPhone;
        this.email=email;
        this.birthday=birthday;
        this.webSite=webSite;
        this.profession=profession;
    }


    public  ContactPerson(String name,String nickName, String address,String homePhone,String workPhone,String cellPhone,String email, String birthday,String webSite,String profession){

        this.name=name;
        this.nickName=nickName;
        this.address=address;
        this.homePhone=homePhone;
        this.workPhone=workPhone;
        this.cellPhone=cellPhone;
        this.email=email;
        this.birthday=birthday;
        this.webSite=webSite;
        this.profession=profession;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
