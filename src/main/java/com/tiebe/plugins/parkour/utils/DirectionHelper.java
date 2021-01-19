package com.tiebe.plugins.parkour.utils;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;

public class DirectionHelper {

    public enum Direction{
        NORTH, EAST, SOUTH, WEST
    }

    /**
     * Get the cardinal compass direction of an entity.
     *
     * @param sign
     * @return
     */
    public static Direction getCardinalDirection(Sign sign) {
        double rot = (sign.getLocation().getYaw() - 90) % 360;
        if (rot < 0) {
            rot += 360.0;
        }
        return getDirection(rot);
    }

    /**
     * Converts a rotation to a cardinal direction name.
     *
     * @param rot
     * @return
     */
    private static Direction getDirection(double rot) {
        if (0 <= rot && rot < 45) {
            return Direction.WEST;
        } else if (45 <= rot && rot < 135) {
            return Direction.NORTH;
        } else if (135 <= rot && rot < 225) {
            return Direction.EAST;
        } else if (225 <= rot && rot < 315) {
            return Direction.SOUTH;
        } else if (315 <= rot && rot < 360.0) {
            return Direction.WEST;
        } else {
            return null;
        }
    }

}