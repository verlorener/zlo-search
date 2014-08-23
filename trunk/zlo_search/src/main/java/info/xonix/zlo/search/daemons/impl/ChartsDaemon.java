package info.xonix.zlo.search.daemons.impl;

import info.xonix.utils.daemon.Daemon;
import info.xonix.utils.daemon.DaemonBase;
import info.xonix.zlo.search.charts.ChartService;
import info.xonix.zlo.search.spring.AppSpringContext;
import org.apache.log4j.Logger;

/**
 * User: xonix
 * Date: 8/23/14
 * Time: 2:37 PM
 */
public class ChartsDaemon extends DaemonBase implements Daemon {
    private static final Logger log = Logger.getLogger(ChartsDaemon.class);

    private ChartService chartService = AppSpringContext.get(ChartService.class);

    protected ChartsDaemon() {
        super("Charts Daemon");
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

    @Override
    public void perform() {
        while (true) {
            if (isExiting()) {
                break;
            }
            chartService.processNextTask();
        }
    }
}
