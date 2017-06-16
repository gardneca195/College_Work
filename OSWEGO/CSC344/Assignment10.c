/*  Craig Gardner
 *  ASSIGNMENT 1
 *  C - SYSTEM CALLS AND LINKED LIST
 *  SEPTEMBER, 18 2014
 */


#ifndef ASSIGNMENT10

#define Assignment10
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
};struct node *start;


char* makestring(char* s)
{

	char *contents = (char *)malloc(sizeof(s));
    int i;
	for(i = 0; i<sizeof(s); i++){
	    contents[i] = s[i];
	}
	return (char*)contents;
}


void insert(char* key, char* contents){
	struct node *new_node,*curr;
	new_node = (struct node*)malloc(sizeof(struct node));
	strcpy(new_node->key,key);
	strcpy(new_node->contents, contents);
	new_node->next = NULL;
	if(start == NULL){
		start = new_node;
		curr = new_node;
	}
	else{
		curr->next = new_node;
		curr = new_node;
	}

}


void getkey(char* input)
{
	struct node* temp = start;
	int i;
	while(temp != NULL)
	{
		if(strcmp(input,temp->key)){
			printf("%s : ", temp->key);
			printf("%s\n", temp->contents);
			continue;
		}
	}
		
}
void description(){
	printf("Enter to get system information\n\n");	
	printf("\n       \"progname\" to retrieve the program name\n");
	printf("       \"user\" to retrieve the login name of the user\n");
	printf("       \"host\" to retrieve the name of the machine the program is running on\n");
	printf("       \"uid\"  to retrieve the userid#\n");
	printf("       \"tty\"  to retrieve the user's current terminal\n");
	printf("       \"date\" to retrieve the current day and time\n");
	printf("       \"cwd\"  to retrieve the current working directory\n");
	printf("       \"files\" to retrieve the # of files in the current directory\n");
	printf("       \"term\" to retrieve the user's terminal type\n");
	printf("       \"args\" to retrieve the total number of chars of all program arguments\n");
	printf("       \"exit\" to exit program\n\nEnter: ");
}

void print(){
	char input[256];	
	description();
	fgets(input,sizeof(input), stdin);	
	while(strcmp(input,"exit") != 0 ){
				
		if(strcmp(input,"progname") == 0){
			getkey(input);
		}else if(strcmp(input,"user")==0){
			getkey(input);
		}else if(strcmp(input,"host")==0){
			getkey(input);
		}else if(strcmp(input,"uid")==0){
			getkey(input);
		}else if(strcmp(input,"tty")==0){
			getkey(input);
		}else if(strcmp(input,"date")==0){
			getkey(input);
		}else if(strcmp(input,"cwd")==0){
			getkey(input);
		}else if(strcmp(input,"files")==0){
			getkey(input);
		}else if(strcmp(input,"term")==0){
			getkey(input);		
		}else if(strcmp(input,"args")==0){
			getkey(input);
		}else{
		printf("Enter : ");
		scanf("%s", input);
		}		
	}
	printf("\n");
}



int main(int argc, char **argv)
{

	insert("progname", argv[0]);

    insert("user", getlogin()); 
    char hostname[300];
    gethostname(hostname,300);
    insert("host", hostname);
    
    //uid = (char*)n;
	insert("uid", "uid"); //FIXME

    insert("tty", "ttyname");

    time_t result = time(NULL);
    
    char*time = asctime(localtime(&result));;
    insert( "date", time);

    char* cwd;
    cwd = getcwd(0,0);
    insert( "cwd", cwd);

    DIR *dirp;
    struct dirent *ep;
    dirp = opendir("./");
    char files[300];
    int numf = readdir(dirp)->d_reclen;
    sprintf(files,"%d",numf);
    insert( "files", files);

    insert( "term", getenv("PATH"));

    int count;
    int numc = sizeof(argv[0]);
    if(argc > 1){
        for(count = 1; count<argc;count++){
            numc+=sizeof(argv[count]);
        }
    }
    char c[128];
    sprintf(c,"%d",numc);
    insert( "args", c);

    

     

    print();
    free(start);


}
#endif