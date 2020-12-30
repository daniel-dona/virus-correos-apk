package com.citrix.mvpn.helper;

import java.net.InetSocketAddress;

/* compiled from: PG */
public final class MvpnProxy {
    public final String proxyId;
    public final InetSocketAddress socketAddress;

    public MvpnProxy(String str, InetSocketAddress inetSocketAddress) {
        this.proxyId = str;
        this.socketAddress = inetSocketAddress;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MvpnProxy)) {
            return false;
        }
        MvpnProxy mvpnProxy = (MvpnProxy) obj;
        String proxyId2 = getProxyId();
        String proxyId3 = mvpnProxy.getProxyId();
        if (proxyId2 != null ? !proxyId2.equals(proxyId3) : proxyId3 != null) {
            return false;
        }
        InetSocketAddress socketAddress2 = getSocketAddress();
        InetSocketAddress socketAddress3 = mvpnProxy.getSocketAddress();
        return socketAddress2 != null ? socketAddress2.equals(socketAddress3) : socketAddress3 == null;
    }

    public String getProxyId() {
        return this.proxyId;
    }

    public InetSocketAddress getSocketAddress() {
        return this.socketAddress;
    }

    public int hashCode() {
        String proxyId2 = getProxyId();
        int i = 43;
        int hashCode = proxyId2 == null ? 43 : proxyId2.hashCode();
        InetSocketAddress socketAddress2 = getSocketAddress();
        int i2 = (hashCode + 59) * 59;
        if (socketAddress2 != null) {
            i = socketAddress2.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder a = Eo.a("MvpnProxy(proxyId=");
        a.append(getProxyId());
        a.append(", socketAddress=");
        a.append(getSocketAddress());
        a.append(")");
        return a.toString();
    }
}
