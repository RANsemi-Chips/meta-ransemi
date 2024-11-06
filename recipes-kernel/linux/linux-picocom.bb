require linux-mainline-common.inc

SUMMARY = "Pico ast-v5_2_0 Linux 5.4"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd917c9398086aedcd7e46"

KERNEL_VERSION_SANITY_SKIP = "1"

SRCREV = "${AUTOREV}"
BRANCH = "master"
SRC_URI = "git://github.com/sam-huxtable/linux_Latest.git;protocol=https;branch=${BRANCH} \
           file://pc805_defconfig \
           file://pc805.dts \
          "

do_configure:append:pc805() {
    mkdir -p ${S}/arch/riscv/boot/dts/picocom
    cp ${WORKDIR}/pc805.dts ${S}/arch/riscv/boot/dts/picocom
}

LINUX_VERSION ?= "v5.4.147"
LINUX_VERSION_EXTENSION:append:pc805 = "-pc805"

KERNEL_DEFCONFIG:pc805 = "${WORKDIR}/pc805_defconfig"

COMPATIBLE_MACHINE = "(pc805)"
