const apiBaseURL = '/api'; // ya se encuentra registrada en el archivo vite.config.mts

export async function get(endpoint: string) {
  try {
    const response = await fetch(`${apiBaseURL}/${endpoint}`);
    const data = await response.json();
    return Array.isArray(data) ? data : [data];
  } catch (error) {
    console.error(`Error fetching ${endpoint}:`, error);
    throw error;
  }
}

export async function post(endpoint: string, data: any) {
  try {
    const response = await fetch(`${apiBaseURL}/${endpoint}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    });
    return response.json();
  } catch (error) {
    console.error(`Error posting to ${endpoint}:`, error);
    throw error;
  }
}
