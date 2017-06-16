/*  Craig Gardner
 *  ASSIGNMENT 1
 *  C - SYSTEM CALLS AND LINKED LIST
 *  SEPTEMBER, 18 2014
 */


#ifndef ASSIGNMENT1

#define Assignment1
#define _OPEN_SYS
#include <pwd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/unistd.h>
#include <sys/stat.h>
#include <time.h>
#include <dirent.h>
#include <stddef.h>




struct node{
	char* key;
	char* contents;
	struct node *next;
}node;


char* makestring(char* s)
{

	char *contents = (char *)malloc(sizeof(s));
    int i;
	for(i = 0; i<sizeof(s); i++){
	    contents[i] = s[i];
	}
	return (char*)contents;
}


void insert(struct node *pointer, char* key, char* contents){
	
	/*Iterate through the list until we encounter the last node.*/
	while(pointer->next!=NULL)
	{
		pointer = pointer->next;
	}
	/* Allocatememory for the new node and put the data in it.*/
	pointer->next = (node *)malloc(sizeof(node));
	pointer = pointer->next;
	pointer->key = (char*)makestring(key);
	pointer->contents = (char*)makestring(contents);
	pointer->next = NULL;
}


void print(node *pointer){
	if(pointer==NULL)
	{
		return;
	}
	printf("%s : ", (char*)pointer->key);
	printf("%s \n",(char*) pointer->contents);
	print(pointer->next);
}



int main(int argc, char **argv)
{

	/* start always points to the first node of the linked list.
	   temp is used to point to thte last node of the linked list.*/
	node *temp;
	start = (struct node *)malloc(sizeof(node));
	temp = start;
	temp->next = NULL;
	start = temp;

	insert(temp,"progname", argv[0]);

    insert(temp,"user", getlogin()); 
    char hostname[128];
    gethostname(hostname,128);
    insert(temp,"host", hostname);
    
    //uid = (char*)n;
	insert(temp,"uid", "uid"); //FIXME

    insert(temp,"tty", "ttyname");

    time_t result = time(NULL);
    
    char*time = asctime(localtime(&result));;
    insert(temp, "date", time);

    char* cwd;
    cwd = getcwd(0,0);
    insert(temp, "cwd", cwd);

    DIR *dirp;
    struct dirent *ep;
    dirp = opendir("./");
    char files[128];
    int numf = readdir(dirp)->d_reclen;
    sprintf(files,"%d",numf);
    insert(temp, "files", files);
    int count,numc;
    insert(temp, "term", getenv("PATH"));
    if(argc > 1){
        for(count = 1; count<argc;count++){
            numc+=sizeof(argv[count]);
        }
    }
    char c[128];
    sprintf(c,"%d",numc);
    insert( "args", c);
     
	print(start->next);


}
#endif
