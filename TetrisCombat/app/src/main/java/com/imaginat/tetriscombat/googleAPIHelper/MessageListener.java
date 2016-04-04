package com.imaginat.tetriscombat.googleAPIHelper;

import android.util.Log;

import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nat on 4/4/16.
 */
public class MessageListener implements RealTimeMessageReceivedListener {
    Map<String, Integer> mParticipantScore = new HashMap<String, Integer>();
    // The participants in the currently active game
    ArrayList<Participant> mParticipants = null;


    String mRoomId = null;

    public String getRoomId() {
        return mRoomId;
    }

    public void setRoomId(String roomId) {
        mRoomId = roomId;
    }

    public ArrayList<Participant> getParticipants() {
        return mParticipants;
    }

    public void setParticipants(ArrayList<Participant> participants) {
        mParticipants = participants;
    }
    @Override
    public void onRealTimeMessageReceived(RealTimeMessage rtm) {
        Log.d("MessageListener","onRealTimeMessageReceived");
        byte[] buf = rtm.getMessageData();
        String sender = rtm.getSenderParticipantId();
        Log.d("GoogleHelper", "Message received: "+sender+" " + (char) buf[0] + "/" + (int) buf[1]);

        if (buf[0] == 'F' || buf[0] == 'U') {
            // score update.
            int existingScore = mParticipantScore.containsKey(sender) ?
                    mParticipantScore.get(sender) : 0;
            int thisScore = (int) buf[1];
            if (thisScore > existingScore) {
                // this check is necessary because packets may arrive out of
                // order, so we
                // should only ever consider the highest score we received, as
                // we know in our
                // game there is no way to lose points. If there was a way to
                // lose points,
                // we'd have to add a "serial number" to the packet.
                mParticipantScore.put(sender, thisScore);
                Log.d("GoogleHelper","onRealTimeMessageReceived with "+thisScore);
            }

            // update the scores on the screen
            updatePeerScoresDisplay();

            // if it's a final score, mark this participant as having finished
//            // the game
//            if ((char) buf[0] == 'F') {
//                mFinishedParticipants.add(rtm.getSenderParticipantId());
//            }
        }
    }

    // updates the screen with the scores from our peers
    void updatePeerScoresDisplay() {

        int i=0;
        if (mRoomId != null) {
            for (Participant p : mParticipants) {
                String pid = p.getParticipantId();
                //if (pid.equals(mMyId))
                //    continue;
                if (p.getStatus() != Participant.STATUS_JOINED)
                    continue;
                int score = mParticipantScore.containsKey(pid) ? mParticipantScore.get(pid) : 0;
                Log.d("MessageListener",p.getDisplayName()+" score "+score);

                ++i;
            }
        }
/*
        if (mRoomId != null) {
            for (Participant p : mParticipants) {
                String pid = p.getParticipantId();
                if (pid.equals(mMyId))
                    continue;
                if (p.getStatus() != Participant.STATUS_JOINED)
                    continue;
                int score = mParticipantScore.containsKey(pid) ? mParticipantScore.get(pid) : 0;
                ((TextView) findViewById(arr[i])).setText(formatScore(score) + " - " +
                        p.getDisplayName()+" good job");
                ++i;
            }
        }

        for (; i < arr.length; ++i) {
            ((TextView) findViewById(arr[i])).setText("");
        }*/
    }

}


