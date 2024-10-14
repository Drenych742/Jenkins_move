def FOLDER_NAME = 'Папка, куда переместить проекты, например "2ndDIR" '
def JOB_REGEX = 'Регулярное выражение Java, какие проекты перемещать, например, начинающиеся на имя 44.1 "^44\\.1.*" '

import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.*

jenkins = Jenkins.instance

def folder = jenkins.getItemByFullName(FOLDER_NAME)
if (folder == null) {
  println "ERROR: Folder '$FOLDER_NAME' not found"
  return
}

// Find jobs in main folder
def found = jenkins.items.grep { it.name =~ "${JOB_REGEX}" }
println "Searching main folder : $found"



// Move them
found.each { job ->
  println "Moving '$job.name' to '$folder.name'"
  Items.move(job, folder)
}
