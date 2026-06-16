package com.smartindustries.smartlock.platform.spacemanagement.interfaces.rest.resources;

public record DeviceWithSiteResource(Long id, Long siteId, String siteName, String siteDescription, String name, String status, String mode) {
}
