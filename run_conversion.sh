#!/bin/bash

# your java compiler path
#JP=~/opt/java-8-openjdk-amd64/bin/

# check java compiler
${JP}javac -version

if [ $? != 0 ]; then
	echo "ERROR: No java compiler found."
	echo "Modify JP variable in this script if you have javac installed."
	exit 1
fi

# uncomment to recompile the tool even if it exista
# rm bin/*

# clear output directory
rm -rf Converted
mkdir -p Converted

# compile the tool
if [ ! -f bin/KicadModuleToGEDA.class ]; then
    ${JP}javac *.java
	# check compilation failed
	if [ $? != 0 ]; then
		exit 1
	fi

	# move the compiled binaries to the bin directory
    mkdir -p bin
    mv *.class bin
fi

# find all kicad modules
echo "Searching for KiCad Modules..."

#find modules sub-directories
MODULE_DIRS=`find  /usr/share/kicad/modules/ -maxdepth 1 -mindepth 1 -type d`

#process all kicad module subdirectories
for KDIR in ${MODULE_DIRS}; do
	SUBDIR=$(basename ${KDIR})
	echo "*** Module dir : ${SUBDIR}"
	MODULES=`find ${KDIR} -type f -name "*.kicad_mod"`
	#create output subdirectory
	OUTDIR=Converted/${SUBDIR}
	mkdir -p ${OUTDIR} 
	# convert each and every module file
	for KMODULE in ${MODULES} ; do
		NAME=$(basename "${KMODULE%.*}")
		echo "Converting module: ${NAME}"
		${JP}java -cp bin KicadModuleToGEDA -q -nosummary -k ${KMODULE} -d ${OUTDIR}/
	done
done