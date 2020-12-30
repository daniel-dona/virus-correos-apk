package com.citrix.auth.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.citrix.auth.exception.AuthenticationException;

/* compiled from: PG */
public class AuthResult implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public AuthResult createFromParcel(Parcel parcel) {
            return new AuthResult(parcel);
        }

        public AuthResult[] newArray(int i) {
            return new AuthResult[i];
        }
    };
    public AuthenticationException exception;
    public int success;
    public Token token;

    public AuthResult(int i, Token token2, AuthenticationException authenticationException) {
        this.success = i;
        this.token = token2;
        this.exception = authenticationException;
    }

    public int describeContents() {
        return 0;
    }

    public AuthenticationException getException() {
        return this.exception;
    }

    public int getSuccess() {
        return this.success;
    }

    public Token getToken() {
        return this.token;
    }

    public String toString() {
        StringBuilder a = Eo.a("AuthResult{success='");
        a.append(this.success);
        a.append('\'');
        a.append(", token='");
        a.append(this.token);
        a.append('\'');
        a.append(", exception='");
        a.append(this.exception);
        a.append('\'');
        a.append('}');
        return a.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.success);
        parcel.writeSerializable(this.token);
        parcel.writeSerializable(this.exception);
    }

    public AuthResult(Parcel parcel) {
        this.success = parcel.readInt();
        this.token = (Token) parcel.readSerializable();
        this.exception = (AuthenticationException) parcel.readSerializable();
    }
}
