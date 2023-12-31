package com.multicampus.cleancode.htmlunit;

import com.multicampus.cleancode.htmlunit.compile.PageCrawlerImpl;
import com.multicampus.cleancode.htmlunit.compile.PageData;
import com.multicampus.cleancode.htmlunit.compile.PathParser;
import com.multicampus.cleancode.htmlunit.compile.SuiteResponder;
import com.multicampus.cleancode.htmlunit.compile.WikiPage;
import com.multicampus.cleancode.htmlunit.compile.WikiPagePath;

public class HtmlUnit {
	public static String testableHtml(PageData pageData, boolean includeSuiteSetup) throws Exception {
		WikiPage wikiPage = pageData.getWikiPage();
		StringBuffer buffer = new StringBuffer();
		if (pageData.hasAttribute("Test")) {
			if (includeSuiteSetup) {
				WikiPage suiteSetup = PageCrawlerImpl.getInheritedPage(SuiteResponder.SUITE_SETUP_NAME, wikiPage);
				if (suiteSetup != null) {
					WikiPagePath pagePath = suiteSetup.getPageCrawler().getFullPath(suiteSetup);
					String pagePathName = PathParser.render(pagePath);
					buffer.append("!include -setup .").append(pagePathName).append("\n");
				}
			}
			WikiPage setup = PageCrawlerImpl.getInheritedPage("SetUp", wikiPage);
			if (setup != null) {
				WikiPagePath setupPath = wikiPage.getPageCrawler().getFullPath(setup);
				String setupPathName = PathParser.render(setupPath);
				buffer.append("!include -setup .").append(setupPathName).append("\n");
			}
		}
		buffer.append(pageData.getContent());
		if (pageData.hasAttribute("Test")) {
			WikiPage teardown = PageCrawlerImpl.getInheritedPage("TearDown", wikiPage);
			if (teardown != null) {
				WikiPagePath tearDownPath = wikiPage.getPageCrawler().getFullPath(teardown);
				String tearDownPathName = PathParser.render(tearDownPath);
				buffer.append("\n").append("!include -teardown .").append(tearDownPathName).append("\n");
			}
			if (includeSuiteSetup) {
				WikiPage suiteTeardown = PageCrawlerImpl.getInheritedPage(SuiteResponder.SUITE_TEARDOWN_NAME, wikiPage);
				if (suiteTeardown != null) {
					WikiPagePath pagePath = suiteTeardown.getPageCrawler().getFullPath(suiteTeardown);
					String pagePathName = PathParser.render(pagePath);
					buffer.append("!include -teardown .").append(pagePathName).append("\n");
				}
			}
		}
		pageData.setContent(buffer.toString());
		return pageData.getHtml();
	}
}