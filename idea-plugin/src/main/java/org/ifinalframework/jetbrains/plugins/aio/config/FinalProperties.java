package org.ifinalframework.jetbrains.plugins.aio.config;


import org.ifinalframework.jetbrains.plugins.aio.issue.IssueProperties;

/**
 * FinalProperties
 *
 * @author iimik
 * @since 1.6.0
 **/
public class FinalProperties {
    private IssueProperties jira;

    public IssueProperties getJira() {
        return jira;
    }

    public void setJira(IssueProperties jira) {
        this.jira = jira;
    }
}
