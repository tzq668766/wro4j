/*
 * Copyright (c) 2008. All rights reserved.
 */
package ro.isdc.wro.resource.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ro.isdc.wro.resource.UriLocator;

/**
 * UriLocator capable to read the resources from some URL. Usually, this
 * uriLocator will be the last in the chain of uriLocators.
 *
 * @author Alex Objelean
 * @created Created on Nov 10, 2008
 */
public final class UrlUriLocator implements UriLocator {
  /**
   * Logger for this class.
   */
  private static final Logger log = LoggerFactory.getLogger(UrlUriLocator.class);

  /**
   * {@inheritDoc}
   */
  public boolean accept(final String uri) {
    return isValid(uri);
  }


	/**
	 * Check if a uri is a URL resource.
	 *
	 * @param uri to check.
	 * @return true if the uri is a URL resource.
	 */
	public static boolean isValid(final String uri) {
		// if creation of URL object doesn't throw an exception, the uri can be
		// accepted.
		try {
			new URL(uri);
		} catch (final MalformedURLException e) {
			return false;
		}
    return true;
  }

  /**
   * {@inheritDoc}
   */
  public InputStream locate(final String uri) throws IOException {
    if (uri == null) {
      throw new IllegalArgumentException("uri cannot be NULL!");
    }
    log.debug("Reading uri: " + uri);
    final URL url = new URL(uri);
    log.debug(url.getProtocol());
    return url.openStream();
  }
}
