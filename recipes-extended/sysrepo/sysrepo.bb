SUMMARY = "YANG-based configuration and operational state data store for Unix/Linux applications."
DESCRIPTION = ""
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ef345f161efb68c3836e6f5648b2312f"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

inherit cmake pkgconfig python3native python3-dir

SRC_URI = "git://github.com/sysrepo/sysrepo.git;protocol=https;branch=devel \
    file://sysrepo_SYS_futex_compile_issue.patch \
    file://sysrepo"

PV = "2.2.60+git${SRCPV}"
SRCREV = "9333e0fc0ee25b3f955da94ab496287b442b7fcd"

S = "${WORKDIR}/git"

DEPENDS = "libyang libnetconf2"

FILES:${PN} += "/usr/share/yang/* /usr/lib/sysrepo-plugind/*"

EXTRA_OECMAKE = " -DCMAKE_INSTALL_PREFIX:PATH=/usr -DCMAKE_BUILD_TYPE:String=Release -DBUILD_EXAMPLES:String=False -DENABLE_TESTS:String=False -DREPOSITORY_LOC:PATH=/etc/sysrepo  -DCALL_TARGET_BINS_DIRECTLY=False -DGEN_LANGUAGE_BINDINGS:String=False "

do_install:append () {
    install -d ${D}/etc/sysrepo/data/notifications
    install -d ${D}/etc/sysrepo/yang
    install -o root -g root ${S}/modules/ietf-netconf-notifications.yang ${D}/etc/sysrepo/yang/ietf-netconf-notifications@2012-02-06.yang
    install -o root -g root ${S}/modules/ietf-netconf-with-defaults.yang ${D}/etc/sysrepo/yang/ietf-netconf-with-defaults@2011-06-01.yang
    install -o root -g root ${S}/modules/ietf-netconf.yang ${D}/etc/sysrepo/yang/ietf-netconf@2011-06-01.yang
    install -d ${D}/etc/init.d
    install -d ${D}/usr/lib/sysrepo/plugins
    install -o root -g root ${WORKDIR}/sysrepo ${D}/etc/init.d/sysrepo
}
