package com.example.etc.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User implements UserDetails {
    private Integer id;
    private String username;
    @JsonIgnore
    private String password;
    private String accountStatus;
    private String cardNumber;
    private String passportCountry;
    private Integer termVersion;
    private boolean isKyc;
    private boolean isOtp;
    private boolean isBankConnect;
    private boolean isSuitTest;
    private boolean isBankApprove;
    private boolean isUpload;
    private boolean isVerifyEmail;
    @JsonIgnore
    private String pin;
    private Boolean isNonLocked;
    private Boolean isEnabled;
    private String kycRequestId;
    private int invalidAuthCount;
    private int invalidPinCount;
    private int invalidOTPCount;
    private int pdpaVersion;
    private boolean isFcnUpload;
    @Basic
    @Column(name = "profile_image_url")
    private String profileImageUrl;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Basic
    @Column(name = "created_date")
    private Date createdDate;
    @CreatedBy
    @Basic
    @Column(name = "created_by")
    private String createdBy;
    @LastModifiedBy
    @Basic
    @Column(name = "updated_by")
    private String updatedBy;
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Basic
    @Column(name = "updated_date")
    private Date updatedDate;

    private Collection<SimpleGrantedAuthority> authorities;

    private Collection<Role> roles;

    private UserRole userRole;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Override
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    @JsonIgnore
    public String getPassword() {
        return password;
    }


    @JsonSetter
    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "card_number")
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Basic
    @Column(name = "passport_country")
    public String getPassportCountry() {
        return passportCountry;
    }

    public void setPassportCountry(String passportCountry) {
        this.passportCountry = passportCountry;
    }

    @Basic
    @Column(name = "is_non_locked")
    public Boolean isNonLocked() {
        return isNonLocked;
    }

    public void setNonLocked(Boolean nonLocked) {
        isNonLocked = nonLocked;
    }

    @Basic
    @Column(name = "is_enabled")
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Transient
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (this.authorities == null || this.authorities.isEmpty()) {
            this.authorities = new ArrayList<>();
            // Add all roles
            this.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });

            // Add all authorities
            this.authorities.addAll(
                    this
                            .getRoles()
                            .stream()
                            .flatMap(role -> {
                                return role.getAuthorities().stream();
                            })
                            .distinct()
                            .map(a -> new SimpleGrantedAuthority(a.getName()))
                            .collect(Collectors.toList())
            );
        }

        return this.authorities;
    }

    public void setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Transient
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Transient
    @Override
    public boolean isAccountNonLocked() {
        return this.isNonLocked;
    }

    @Transient
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Basic
    @Column(name = "account_status")
    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Basic
    @Column(name = "is_kyc")
    public boolean isKyc() {
        return isKyc;
    }

    public void setKyc(boolean kyc) {
        isKyc = kyc;
    }

    @Basic
    @Column(name = "is_otp")
    public boolean isOtp() {
        return isOtp;
    }

    public void setOtp(boolean otp) {
        isOtp = otp;
    }

    @Basic
    @Column(name = "term_version")
    public Integer getTermVersion() {
        return termVersion;
    }

    public void setTermVersion(Integer termVersion) {
        this.termVersion = termVersion;
    }

    @Basic
    @Column(name = "pin")
    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Basic
    @Column(name = "is_bank_connect")
    public boolean isBankConnect() {
        return isBankConnect;
    }

    public void setBankConnect(boolean bankConnect) {
        isBankConnect = bankConnect;
    }

    @Basic
    @Column(name = "is_suit_test")
    public boolean isSuitTest() {
        return isSuitTest;
    }

    public void setSuitTest(boolean suitTest) {
        isSuitTest = suitTest;
    }

    @Basic
    @Column(name = "is_bank_approve")
    public boolean isBankApprove() {
        return isBankApprove;
    }

    public void setBankApprove(boolean bankApprove) {
        isBankApprove = bankApprove;
    }

    @Basic
    @Column(name = "is_upload")
    public boolean isUpload() {
        return isUpload;
    }

    public void setUpload(boolean upload) {
        isUpload = upload;
    }

    @Basic
    @Column(name = "kyc_request_id")
    public String getKycRequestId() {
        return kycRequestId;
    }

    public void setKycRequestId(String kycRequestId) {
        this.kycRequestId = kycRequestId;
    }

    @Basic
    @Column(name = "invalid_auth_count")
    public int getInvalidAuthCount() {
        return invalidAuthCount;
    }

    public void setInvalidAuthCount(int invalidAuthCount) {
        this.invalidAuthCount = invalidAuthCount;
    }

    @Basic
    @Column(name = "invalid_pin_count")
    public int getInvalidPinCount() {
        return invalidPinCount;
    }

    public void setInvalidPinCount(int invalidPinCount) {
        this.invalidPinCount = invalidPinCount;
    }

    @Basic
    @Column(name = "invalid_otp_count")
    public int getInvalidOTPCount() {
        return invalidOTPCount;
    }

    public void setInvalidOTPCount(int invalidOTPCount) {
        this.invalidOTPCount = invalidOTPCount;
    }

    @Basic
    @Column(name = "pdpa_version")
    public int getPdpaVersion() {
        return pdpaVersion;
    }

    public void setPdpaVersion(int pdpaVersion) {
        this.pdpaVersion = pdpaVersion;
    }

    @Basic
    @Column(name = "is_verify_email")
    public boolean isVerifyEmail() {
        return isVerifyEmail;
    }

    public void setVerifyEmail(boolean verifyEmail) {
        isVerifyEmail = verifyEmail;
    }

    @Basic
    @Column(name = "is_fcn_upload")
    public boolean isFcnUpload() {
        return isFcnUpload;
    }

    public void setFcnUpload(boolean fcnUpload) {
        isFcnUpload = fcnUpload;
    }
}
