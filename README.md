# NavDemo


Lab Navigation Architecture Component
Objectives:

    Understand Navigation Component
    Integrate navigation graphing features
    Design and implement app containing multiple screens
    Allow users to move among screens and pass data using type-safe arguments


In order to prevent some unnecessary configuration errors, you may want to upgrade your IDE and libraries to the latest versions through SDK Manager. Of course you also need to make sure you have configured a corresponding emulator.

ViewBinding is not used in this lab.

Step 1: Use Empty Activity to create a project, NavDemo, by using API 30 as the Minimum SDK and make sure your language is Java
Step 2: Create navigation graph resource file
Step 2.1: Right click res and select New -> Android Resource File
Step 2.2: Configure the attributes as follows:
File Name: navgraph
Resource type: Select “Navigation” as the Resource type
Then click OK button
For this step, you can also use Resource Manager (on the left panel in the Studio)
In Resource Manager click “Navigation” menu item and click Plus (+) button and select Navigation Resource File to fill up the File name as mentioned above
No matter what, after OK button is clicked, if the system prompts to Add Project Dependency.
Take a screenshot if the system shows some inconsistencies for debugging later on.
Then click OK.
Maybe you want to launch your app to make sure so far the app runs on your emulator.
Now you will see navgrpah is the only element in Component Tree and navgraph.xml is created under res/navigation. You may want to take a look at the code of navgraph.xml (under Code mode)
You may also want to open build.gradle(Module) to make sure navigation dependencies are added
Step 3: Add navigation host to the project
Step 3.1: Open activity_main.xml in Design mode
Step 3.2: Delete TextView (i.e, Hello World) in Component Tree
Step 3.3: In the Palette, select Containers, and drag NavHostFragment into Component Tree underneath ConstraintLayout (i.e., activity_main.xml)
Step 3.4: In “Navigation Graphs” dialog, select navgraph, and click on OK button
Step 3.5: Switch to Code mode to view the xml code for FragmentContainerView with NavHostFragment
Step 4: Switch to navgraph.xml in Design mode to see that fragmentContainerView in activity_main is found and identified under Hosts (at the upper left hand side and above the Component Tree).

You may need to close and reopen navgraph.xml to see that activity_main is detected as a host.

Step 5: Add Navigation Destinations
Step 5.1: Create first fragment
Step 5.1.1: Click “New Destination” (The left most menu item above the editor)
Step 5.1.2: Click “Create new destination”
Step 5.1.3: Select Fragment (Blank), click on “Next” button
Step 5.1.4: Type “Frag1” in Fragment Name, click on “Finish” button
Now you will see frag1 is listed underneath navgraph in Component Tree.
If you do not see Home icon next to frag1 in the Editor to indicate frag1 as the start destination, you can select farg1 in the Editor and click the “Assign Start Destination” in the icon menu on the top of the Editor.
Step 6: Follow steps described in Step 5 to create the second fragment and name it as “Frag2”.
After Step 6, you should have two fragments under navgraph in Component Tree. frag2 is also shown in the Editor.
Step 7: Prepare widgets for fragment_frag1
Step 7.1: Open fragment_frag1.xml under layout
Step 7.2: Remove “Hello bank fragment” TextView in FrameLayout
Step 7.3: Right click FrameLayout in Component Tree and select “Convert FrameLayout to ConstraintLayout”. Click on OK button to flatten the hierarchy if prompted.
Now the layout manager will be a ConstraintLayout but the name (i.e., id) in Component Tree may still be frameLayout. That is fine. You can go to Code mode to validate that it is a ConstraintLayout now.
Step 7.4: Drag in an EditText (i.e., Plain Text) and Button into Frag1. (That is, drag in to Component Tree)
Step 7.5: Modify attributes for the edit text and button
editText: Delete text, which is “Name” (i.e., keep the text field be blank)
button: Make the id for the button to be “btn_submit”.
Step 7.6 Since we do not define constraints for the edit text and the button, you can use Infer Constraints (i.e., the magic wand on top the Editor pane) to remove missing constraints error. Or you can define the constraints yourself. The bottom line is to make the errors away.
Again, as mentioned above you may see frameLayout under Component Tree. If you review the code, you will find it is the id. The layout has been converted to Constraint Layout (from Frame Layout)
Step 8: Add action to Navigation Graph between farg1 and frag2
Step 8.1: Open navgraph.xml in Design Mode
Step 8.2: Drag the vertical center circle on the right hand edge of frag1 to link with frag2. Notice that id is set for the link. Also make sure the destination underneath the id is set to frag2. If you switch to Code view, you will see action element is added inside frag1.
Step 9: Trigger action
Step 9.1: Open Frag1.java
Step 9.2: Replace the code in onCreateView method as follows: (Not onCreate(). Don’t define it at the wrong place. Make sure you do not leave the original return statement there. I comment it out.)
// Inflate the layout for this fragment

View local_view = inflater.inflate(R.layout.fragment_frag1, container, false);

Button button = local_view.findViewById(R.id.btn_submit);

button.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
Navigation.findNavController(view).navigate(R.id.action_frag1_to_frag2);
}
});

//return inflater.inflate(R.layout.fragment_frag1, container, false);
return local_view;

Step 10: Run your app and click the button to swap to frag2.
Your screen should show “Hello blank fragment” because that is the text defined in fragment_frag2.xml.
The next objective is to pass data from frag1 to frag2.
Step 11: Passing data using Safeargs
Step 11.1: Open navgraph.xml
Step 11.2: Highlight frag2 in Component Tree
Step 11.3: In the Attributes panel on the right hand side, click “+” icon in the field called “Arguments” and define attributes in the “Add Argument” dialog as follows:
Name: mesg
Type: String
Default Value: type in “No Message”
Then click the “Add” button
You can switch to Code mode to see the newly created argument.
Step 12: Add safeargs plugin to the gradle build configuration
Step 12.1: In Project tool window, open build.gradle(Project) under Gradle Scripts
Step 12.2: Declare buildscript to define the classpath dependency
buildscript {
dependencies {
classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
}
}

// Just keep the version consistent between gradle(Project) and gradle(Module)
// You can open gradle(Module) and find the version inside the dependencies

Step 12.3: Open build.gradle(Module) define plugin under plugins
id 'androidx.navigation.safeargs'

You can check the latest version on https://developer.android.com/jetpack/androidx/releases/navigation

Step 12.4: Click Sync Now on the upper right hand corner
Notice: The newer versions of these libraries may have been released. To identify and use new versions of the libraries, in Project Structures dialog (File -> Project Structure…), click Suggestions option to display available updates. Click Update button(s) for those libraries used in the build.gradle before clicking on the Apply button. Then click on OK button. Wait for system to finish sync.
Step 13: Open fragment_frag2.xml under layout
Update the attributes for the TextView as follows: (You can use the Attributes panel in the Design mode)
id: argText
text: (clear the text and make it be blank because we will pass in data later on)
Step 14: Open fragment_frag1.xml, define “userText” as the id for the EditTextView
Step 15: Build the project by clicking Build > Make Project  (on the menu bar)
In older IDE version, after the execution you can see Frag1Directions and Frag2Args under java (generated). I did not see it in the current version. That is not good for programmers because we don’t know the real naming scheme that Android uses behind the scene. It helps people to debug at Step 17.2
Step 16: Open Frag1.java and change the code in onClick(View view) as follows:
EditText userText = getView().findViewById(R.id.userText);
Frag1Directions.ActionFrag1ToFrag2 action = Frag1Directions.actionFrag1ToFrag2();

action.setMesg(userText.getText().toString());
Navigation.findNavController(view).navigate(action);

//Navigation.findNavController(view).navigate(R.id.action_frag1_to_frag2);

Notice: When you prepare the code above make sure you use Intellisense editor to key in the code. Otherwise, you may face error. That is, you need to type the code not just copy my code.
For example, the IDE might not recognize navigate unless you select that method through the IDE.
Step 17: Open Frag2.java
Step 17.1: import the TextView library
import android.widget.TextView;

Step 17.2: Define onStart() within Class Frag2 as follows:
@Override
public void onStart(){
super.onStart();

    TextView argText = getView().findViewById(R.id.argText);
    Frag2Args args= Frag2Args.fromBundle(getArguments());
//see comment at step 15 above
String mesg = args.getMesg();
argText.setText(mesg);
}


Of course, you may put the similar code inside onCreateView(). But you need to handle the inflate first. (Check the code in Frag1.java.) Use onStart() will be easier.

Step 18: Compile and run the app and enter some text before clicking on the Button. Take a screen shot. Then click on Button. Take another screen shot to show that the message is passed to frag2.  Embed these two screenshots to a Microsoft Document and submit it for grading.