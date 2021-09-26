package com.example.goalsontrack.Model;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.Exclude;

public class TaskId {
    @Exclude //Excludes the task id from all the manipulations of data
    public String TaskId;

    public <T extends TaskId> T withId(@NonNull final String id){
        this.TaskId = id;
        return (T) this;
    }
}
