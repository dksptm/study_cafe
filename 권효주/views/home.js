// home.js

let template = `
<p :class="myText"> {{ msg }}</p>
`;

export default {
  template,
  data() {
    return {
      msg : 'Welcome! Get USERS information from typicode.',
      myText : 'text-info h3' 
    }
  }
}