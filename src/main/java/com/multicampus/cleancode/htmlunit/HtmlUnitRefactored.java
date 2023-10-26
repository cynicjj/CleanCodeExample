package com.multicampus.cleancode.htmlunit;

import com.multicampus.cleancode.htmlunit.compile.PageData;
import com.multicampus.cleancode.htmlunit.compile.WikiPage;

public class HtmlUnitRefactored {
	public static String renderPageWithSetupsAndTeardowns(PageData pageData, boolean isSuite) throws Exception {
		boolean isTestPage = pageData.hasAttribute("Test");
		if (isTestPage) {
			WikiPage testPage = pageData.getWikiPage();
			StringBuffer newPageContent = new StringBuffer();
			includeSetupPages(testPage, newPageContent, isSuite);
			newPageContent.append(pageData.getContent());
			includeTeardownPages(testPage, newPageContent, isSuite);
			pageData.setContent(newPageContent.toString());
		}
		return pageData.getHtml();
	}

	private static void includeTeardownPages(WikiPage testPage, StringBuffer newPageContent, boolean isSuite) {
		// TODO Auto-generated method stub
		
	}

	private static void includeSetupPages(WikiPage testPage, StringBuffer newPageContent, boolean isSuite) {
		// TODO Auto-generated method stub
		
	}
}
