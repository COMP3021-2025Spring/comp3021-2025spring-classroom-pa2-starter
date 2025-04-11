/*
 * Copyright (c) 2025.
 * Xiang Chen xchenht@connect.ust.hk
 * This project is developed only for HKUST COMP3021 Programming Assignment
 */

package hk.ust.cse.comp3021;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

/**
 * SessionManager class provides read/write interface for session database
 */
public class SessionManager {
    /**
     * The session database in memory as a JSONObject
     */
    static JSONObject db;

    /**
     * The path to the session database file
     */
    static final String dbPath = "db.json";

    /**
     * Load session database from file into memory, create an empty database if not exist
     */
    static void loadDatabase() {
        try {
            Path filePath = Paths.get(dbPath);
            db = new JSONObject(Files.readString(filePath));
            Utils.printlnInfo("Sessions database loaded");
        } catch (IOException | JSONException e) {
            Utils.printlnError("Failed to load the database: " + e.getMessage());
            db = new JSONObject();
            Utils.printlnInfo("Empty sessions database created");
        }
    }

    /**
     * Save session database from memory to file
     */
    static void saveDatabase() {
        try {
            Path filePath = Paths.get(dbPath);
            Files.writeString(filePath, db.toString(4));
            Utils.printlnInfo("Sessions database saved");
        } catch (IOException e) {
            Utils.printlnError("Fail to save sessions database");
        }
    }

    /**
     * Initialize sessions for current user
     *
     * @param user the user
     */
    static void initSessions(String user) {
        if (!db.has(user)) {
            db.put(user, new JSONObject());
        }
    }

    /**
     * Get the session given the user and sessionUID
     *
     * @param user       the user to get session for
     * @param sessionUID the sessionUID to get
     * @return the session
     */
    static JSONObject getSession(String user, String sessionUID) {
        try {
            return db.getJSONObject(user).getJSONObject(sessionUID);
        } catch (JSONException e) {
            Utils.printlnError("Failed to get the session: " + e.getMessage());
        }
        return null;
    }

    /**
     * Save a session to the session database
     *
     * @param user    the user to save session for
     * @param session the session to save
     */
    static void setSession(String user, String sessionUID, JSONObject session) {
        try {
            db.getJSONObject(user).put(sessionUID, session);
        } catch (JSONException e) {
            Utils.printlnError("Failed to save the session: " + e.getMessage());
        }
    }

    /**
     * Get all the sessions of the user as stream
     * TODO: implement me
     *
     * @param user the user to get sessions for
     * @return the sessions
     */
    static Stream<JSONObject> getSessionsStream(String user) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Print the session information
     *
     * @param session the session to print
     */
    static void printSession(String sessionUID, JSONObject session) {
        try {
            String clientName = session.getString("clientName");
            String tags = session.getJSONArray("tags").join(", ");
            String description = session.getString("description");
            String timeCreated = Utils.timeToString(session.getLong("timeCreated"));
            String timeLastExit = Utils.timeToString(session.getLong("timeLastExit"));
            System.out.printf("UID: %s Client: %-20s Created: %s Last Exit: %s Tags: %-30s Description: %s %n",
                    Utils.toInfo(sessionUID), Utils.toInfo(clientName), Utils.toInfo(timeCreated),
                    Utils.toInfo(timeLastExit), Utils.toInfo(tags), Utils.toInfo(description));
        } catch (JSONException e) {
            Utils.printlnError("Error reading session: " + e.getMessage());
        }
    }

    /**
     * List all the previously stored sessions
     * (optional) TODO: implement me
     *
     * @param user the user to list sessions for
     */
    public static void listSessions(String user) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Get the number of users
     *
     * @return the number of users
     */
    public static int getNumUsers() {
        return db.toMap().size();
    }

    /**
     * Get the set of users
     *
     * @return the set of users
     */
    public static Set<String> getUsers() {
        return db.toMap().keySet();
    }

    /**
     * The list of ignored words when counting top words
     */
    static List<String> ignoredWords;

    // Load the stopwords from the file stopwords.txt, remember to filter out comments
    static {
        try {
            ignoredWords = Files.readAllLines(Paths.get("stopwords.txt"));
        } catch (IOException e) {
            Utils.printlnError("Failed to load stopwords: " + e.getMessage());
            ignoredWords = new ArrayList<>();
        }
    }

    /**
     * The unit USD price of a prompt token using one billion parameters
     */
    static final Double unitPromptPrice = 8.82e-09;

    /**
     * The unit USD price of a response token using one billion parameters
     */
    static final Double unitCompletionPrice = 3.53e-08;

    /**
     * Initialize an empty profile
     * TODO: implement me
     *
     * @return the empty profile
     */
    static JSONObject createEmptyProfile() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Update the top string map with new strings
     * TODO: implement me
     *
     * @param topStringMap the top string map, from its
     * @param newStrings   the new strings to add
     * @return the updated top string map
     */
    private static JSONObject updateTopString(JSONObject topStringMap, Stream<String> newStrings) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Keep only the top N strings in the map
     * TODO: implement me
     *
     * @param topNStringMap the top N string map
     * @param topN          the number of top strings to keep
     * @return the updated top N string map
     */
    private static JSONObject limitTopNString(JSONObject topNStringMap, int topN) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Generate a profile for the user and save as json file using reduce
     * Casting the epoch time from Long to Integer is safe because we will not encounter the
     * <a href="https://en.wikipedia.org/wiki/Year_2038_problem">Year 2038 problem</a>.
     * TODO: implement me
     *
     * @param user the user to generate profile for
     */
    static JSONObject generateProfile(String user) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Print the profile in human-readable form
     *
     * @param profile the JSON format profile to print
     */
    private static void printProfile(JSONObject profile) {
        System.out.println(profile.toString(2));
    }

    /**
     * Generate a profile for the user, print and save as json file
     * TODO: implement me
     *
     * @param user the user to generate profile for
     */
    public static void profile(String user) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
