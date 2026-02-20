package com.kawwabi.pearlanium.util;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * this tracker remembers when an enderman's teleport got blocked
 * it's used for the "yes today, i'm sorry" achievement
 * 
 * what needs to happen:
 * 1. player wearing fancy armor
 * 2. enderman tried to teleport but got blocked
 * 3. an arrow bounced back and hit the player within 3 seconds
 */
public class TeleportCancelTracker {
    
    /**
     * how long after teleport blocked that the achievement counts
     * 3 seconds = 3000 milliseconds
     */
    public static final long TELEPORT_CANCEL_WINDOW_MS = 3000L;
    
    // storing when each player's teleport got blocked
    private static final ConcurrentHashMap<UUID, Long> lastTeleportCancelTime = new ConcurrentHashMap<>();
    
    /**
     * remember that this player's armor blocked an enderman teleport
     */
    public static void recordTeleportCancel(UUID playerUuid) {
        lastTeleportCancelTime.put(playerUuid, System.currentTimeMillis());
    }
    
    /**
     * did this happen recently enough to count?
     * @return true if within the last 3 seconds
     */
    public static boolean hasRecentTeleportCancel(UUID playerUuid) {
        Long cancelTime = lastTeleportCancelTime.get(playerUuid);
        if (cancelTime == null) {
            return false;
        }
        
        long elapsed = System.currentTimeMillis() - cancelTime;
        if (elapsed > TELEPORT_CANCEL_WINDOW_MS) {
            // clean up so we don't waste memory
            lastTeleportCancelTime.remove(playerUuid);
            return false;
        }
        
        return true;
    }
    
    /**
     * forget about this player's teleport cancel
     * call this after giving the achievement so it doesn't trigger again
     */
    public static void clearTeleportCancel(UUID playerUuid) {
        lastTeleportCancelTime.remove(playerUuid);
    }
}
