## [link to faceapp git repository](https://github.com/I-am-Shir/Faceapp)
### our workflow:
we created a log in, people can sign in from there (for now there is a default user in case no one signed in- username- Admin@gmail.com, password- a1234567). if they want to sign in they can do it in sign up, which is split into fragments (like in Facebook).
we created a class containing constraints for the sign up and used it to check its validity.
for each stage there is a verification that the input is valid- for signup it checks that it meets the requirements, for login it checks that the user exists and the password is correct.
restrictions: username must be an email- be of pattern letters@letters.letters. password must contain between 8-20 characters, at least one digit, one letter.
must put in a name- first and last which are letters only.
and must put a profile photo- from gallery or camera.
after signing up he's transferred to login for logging in.
after log in he's transferred to the apps feed, containing 10 json posts, each post contains a photo, a name of the poster, and a description.
he can add a post by pressing the plus button (top, third from left), and then he must (in orfer to post) choose a photo from gallery or camera, and write a description.
he can get to menu (three stripes) to see he's contact, move to dark mode, or log out.
he can comment on a post by pressing the comment button, then he can see previous comments or post new ones, plus he can like\edit\delete them.
he can like the post or "share" (doesn't share yet).
he can edit or delete the posts.
he can press on the  search icon which will open a place to input search (the search isn't implemented yet).
we built everything be creating a layout file fot the activity\fragment, and then a java file for the activity\fragment, plus a java file for the adapter of the recycler view.
the xml contains the layout (views, buttons, etc), and the java file contains the logic of the activity\fragment.
the adapter contains the logic of the recycler view.





