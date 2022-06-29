// Declare async function
console.log('hi');
async function getWeatherAW() {
  const result = await fetch(`http://localhost:7050/`);

  const data = await result.json();

console.log(data);
}
