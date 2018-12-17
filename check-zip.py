#!/bin/python

import sys
from zipfile import ZipFile

expected_paths = ["src/"]
required = ["src/smpl/syntax/SMPL_Lexer.jflex",
            "src/smpl/syntax/SMPL_Parser.cup",
            "src/smpl/semantics/SmplEval.java"]
optional = ["examples/"]

def checkFiles(zipfile, file_list):
    found = True
    for fname in file_list:
        print ("  {0:.<50}".format(fname)),
        try:
            reqd_info = zipfile.getinfo(fname)
            if reqd_info.file_size > 0 or fname.endswith("/"):
                print ("[OK]")
            else:
                found = False
                print ("[Failed: 0 byte file]")
        except KeyError:
            found = False
            print("[Failed: Missing]")
    return found

def main(zip_filename):
    manifest_title_format = "%-50s  %-7s"
    manifest_format = "%-50s: %7d"
    with ZipFile(zip_filename, 'r') as zfile:
        info = zfile.infolist()
        suspicious_files = []

        print ("Printing Contents...")
        print (manifest_title_format % ("File Path", "File Size"))
        print ("-" * 61)
        for item in info:
            name, size = item.filename, item.file_size
            print(manifest_format % (name, size))
            if not any(name.startswith(p) for p in expected_paths):
                suspicious_files.append(name)

        print
        if len(required) > 0:
            print ("Checking for required files ...")
            reqd_found = checkFiles(zfile, required)
            if reqd_found:
                print ("All required files found")
            else:
                print ("Error: At least 1 required file missing")

        print
        if len(optional) > 0:
            print ("Checking for optional files ...")
            opt_found = checkFiles(zfile, optional)
            if opt_found:
                print ("All optional files found")
            else:
                print ("Warning: At least 1 optional file missing")

        print ("** Zip File Status **")
        if  reqd_found:
            print ("You may submit your zip file")
        else:
            print ("Your zip file has critical problems.  Fix them before submitting")
        if len(suspicious_files) > 0:
            print ("Warning: The following unexpected files might be ignored:")
            for fname in suspicious_files:
                print("  %s" % fname)
            
def usage():
    instructions = [
        "Usage: python check-zip <path to zipfile>",
        "This program will inspect your zip file for some basic requirements.",
        "If your zip file does not pass the tests administered by this script,",
        "then your zip file is ungradeable, and you should fix it before ",
        "submitting it.",
        "NB: Simply passing the basic tests implemented by this script is a ",
        "prerequisite for being gradeable, but says nothing about the actual",
        "grade that you will eventually get.  Your final grade will depend",
        "largely on the capabilities of your submission."
    ]
    for line in instructions:
        print("%s" % line)
        

if __name__=="__main__":
    if len(sys.argv) != 2:
        usage()
        exit(1)
    else:
        zipfile = sys.argv[1]
        main(zipfile)
    
