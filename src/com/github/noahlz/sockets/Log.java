package com.github.noahlz.sockets;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

class Log {

    private static final Logger LOG = Logger.getLogger("Server");

    static void info(String format, Object... vals) {
        String msg = MessageFormat.format(format, vals);
        LOG.info(msg);
    }

    static void error(String format, Throwable err, Object... vals) {
        String msg = MessageFormat.format(format, vals);
        LOG.log(Level.SEVERE, msg, err);
    }

}
