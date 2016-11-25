package com.nedtech.kipapp;

import android.support.v7.util.SortedList;
import android.util.Log;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smack.util.EventManger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class XMPPManager {
    private static final String TAG = "XMPPManager";
    public static final String DOMAIN = "188.27.175.245"; //server address or IP
    public static final int PORT = 5222; //port server
    public static int conn = 1; //connection trying message...

    private static XMPPManager instance;
    public XMPPTCPConnection mConnection;

    private String username;
    private String password;

    private XMPPManager() {
            XMPPTCPConnectionConfiguration.Builder config = XMPPTCPConnectionConfiguration.builder();
            config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
            config.setServiceName(DOMAIN);
            config.setHost(DOMAIN);
            config.setPort(PORT);
            config.setDebuggerEnabled(true);

            mConnection = new XMPPTCPConnection(config.build());
            mConnection.setPacketReplyTimeout(10000);
        }

    public static XMPPManager getInstance() {
        if (instance == null) {
            instance = new XMPPManager();
        }
        return instance;
    }


    public void connect(final String username, final String password) {
            this.username = username;
            this.password = password;
            reConnect();
    }

    public void reConnect() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mConnection.connect();
                    mConnection.login(username, password);
                    conn=conn+1;

                } catch (SmackException | IOException | XMPPException e) {
                    Log.d("AsyncTask", e.toString());
                }
            }
        });
        thread.start();
    }



}


