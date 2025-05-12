package com.example.collectionBox.dto;

public class BoxResponse {
    private Long id;
    private boolean assigned;
    private boolean empty;

    public BoxResponse(){}

    public BoxResponse(Long id, boolean assigned, boolean empty) {
        this.id = id;
        this.assigned = assigned;
        this.empty = empty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}
