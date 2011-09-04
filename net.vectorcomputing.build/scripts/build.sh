#!/bin/bash

# Determines where this script is located and stores this path in the SCRIPT_PATH variable
SCRIPT_PATH="${BASH_SOURCE[0]}";
if([ -h "${SCRIPT_PATH}" ]) then
	while([ -h "${SCRIPT_PATH}" ]) do SCRIPT_PATH=`readlink "${SCRIPT_PATH}"`; done
fi  
pushd . > /dev/null
cd `dirname ${SCRIPT_PATH}` > /dev/null
SCRIPT_PATH=`pwd`;
popd  > /dev/null

#echo "PWD=${PWD}"
#echo "SCRIPT_PATH=${SCRIPT_PATH}"

source "${SCRIPT_PATH}/platform.sh"
platform_config

# GNU_FIND is the path to a GNU version of the find command
if [ "${KERNELNAME}" == "Darwin" ]; then
	GNU_FIND="gfind"
	CPUTYPE="x86_64"
elif [ "${KERNELNAME}" == "Linux" ]; then
	GNU_FIND="find"
fi

# ECLIPSE_DIR is the path to Eclipse directory with RCP Delta Pack installed
ECLIPSE_DIR="/opt/tools/${KERNELNAME}/${KERNELBITS}/${CPUTYPE}/Eclipse/3.6.2"

# Path to the workspace containing the features and projects #to be imported, compiled, and exported to an update site
WORKSPACE_DIR="${SCRIPT_PATH}/../../"

# The location to save the update site to
DESTINATION="${WORKSPACE_DIR}"

# The to name of update site file
FILENAME_PREFIX="letUpdateSite"
FILENAME_SUFFIX=".zip"
setFileName ()
{
	TIMESTAMP=`date +'%Y%m%d%H%M'`
	FILENAME="${FILENAME_PREFIX}_${TIMESTAMP}${FILENAME_SUFFIX}"
}

setBuildProperties ()
{
	BUILD_PROPERTIES="${WORKSPACE_DIR}/jazzBuild.properties"
	if [ -f "${BUILD_PROPERTIES}" ]; then
		BUILD_PROPERTIES_EXISTED=true;
	else
		BUILD_PROPERTIES_EXISTED=false;
	fi
	echo "FILENAME=${FILENAME}" >> "${BUILD_PROPERTIES}"
	echo "DESTINATION=${DESTINATION}" >> "${BUILD_PROPERTIES}"
}

# Finds the highest version Equinox launcher plugin.
# The name looks like this: org.eclipse.equinox.launcher_1.1.1.R36x_v20101122_1401.jar
setEquinoxPath ()
{
	EQUINOX_JAR=$(${GNU_FIND} "${ECLIPSE_DIR}/plugins" -maxdepth 1 -type f -regextype 'posix-awk' -regex '.*org\.eclipse\.equinox\.launcher_[0-9]+\.[0-9]+\.[0-9]+\.R[0-9]+x_v[[:digit:]]{8}_[[:digit:]]{4}.jar' -printf "%f\n" | sort | tail -n 1)
	EQUINOX_PATH="${ECLIPSE_DIR}/plugins/${EQUINOX_JAR}"
	#echo $EQUINOX_PATH
}

# Finds the highest version PDE build plug-in/directory.
# The name looks like this: org.eclipse.pde.build_3.6.2.R36x_20110203
setPdeBuildPath ()
{
	PDE_BUILD_DIR=$(${GNU_FIND} "${ECLIPSE_DIR}/plugins" -maxdepth 1 -type d -regextype 'posix-awk' -regex '.*org\.eclipse\.pde\.build_[0-9]+\.[0-9]+\.[0-9]+\.R[0-9]+x_[[:digit:]]{8}' -printf "%f\n" | sort | tail -n 1)
	PDE_BUILD_PATH="${ECLIPSE_DIR}/plugins/${PDE_BUILD_DIR}"
	#echo $PDE_BUILD_PATH
}

# Sample Environment Variables
# PWD=/opt/tools/Linux/32/i686/JazzBuildSystem/2.0.0.2iFix5/builds                                                                                                 
# SCRIPT_PATH=/opt/tools/Linux/32/i686/JazzBuildSystem/2.0.0.2iFix5/builds/iet/com.ibm.informix.build/src                                                          
# Buildfile: /opt/tools/Linux/32/i686/JazzBuildSystem/2.0.0.2iFix5/builds/iet/com.ibm.informix.build/src/exportUpdateSite.xml

import ()
{
	java -jar "${EQUINOX_PATH}" \
	-application net.vectorcomputing.build.importProjects \
	-data "${WORKSPACE_DIR}" \
	-clean \
	-XstartOnFirstThread
}

compile ()
{
	java -jar "${EQUINOX_PATH}" \
	-application org.eclipse.ant.core.antRunner \
	-buildfile "${SCRIPT_PATH}/exportUpdateSite.xml" \
	-data "${WORKSPACE_DIR}" \
	-XstartOnFirstThread \
	-Dbuilder="${SCRIPT_PATH}" \
	-DlogExtension=.xml \
	-DpluginPath="${ECLIPSE_DIR}/plugins"
}

PUBLISH_DIR="/export/repo/software/Eclipse/p2/LET/ci"
publish ()
{
	cp "${FILENAME}" "${PUBLISH_DIR}"
}

cleanup ()
{
	if [ ! "${BUILD_PROPERTIES_EXISTED}" ]; then
		rm "${BUILD_PROPERTIES}"
	fi	
}

setFileName
setBuildProperties
setEquinoxPath
setPdeBuildPath

import
compile
#publish
cleanup
