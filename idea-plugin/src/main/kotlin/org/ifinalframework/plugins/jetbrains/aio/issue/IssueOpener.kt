package org.ifinalframework.plugins.jetbrains.aio.issue


/**
 * IssueOpener
 *
 * @author iimik
 * @since 0.0.2
 **/
interface IssueOpener {

    fun supported(issueType: IssueType): Boolean

    fun open(issue: Issue)
}