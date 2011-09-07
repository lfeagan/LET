#!/bin/bash
#Script for converting the format of .properties files in a directory to ascii.
#Parameters Expected:
#  [Encoding]	- Encoding format of the files to convert

ENCODING=$1
TEMPFILE=temp.properties

if [ x${ENCODING} == x ] ; then
	ENCODING=UTF-8
fi

for file in *.properties ; do
	echo $file
	native2ascii -encoding ${ENCODING} $file ${TEMPFILE}
	mv ${TEMPFILE} $file
done