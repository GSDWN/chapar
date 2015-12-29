package com.artronics.chapar.event;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;

public class BaseEvent<M> extends ApplicationEvent
{
    private final static Logger log = Logger.getLogger(BaseEvent.class);
    private final M message;

    public BaseEvent(Object source, M message)
    {
        super(source);
        this.message = message;
    }

    public M getMessage()
    {
        return message;
    }
}

