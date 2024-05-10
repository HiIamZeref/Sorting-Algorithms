import os

try:
    import pandas as pd
    import plotly.express as px
except ImportError:
    # Install libs if not found
    os.system('pip install pandas plotly')
    import pandas as pd
    import plotly.express as px

# Set plotly as the default plotting backend
pd.options.plotting.backend = 'plotly'

# Getting the csv files
algorithms = ['bubble_sort', 'insertion_sort', 'merge_sort', 'quick_sort']
serial_csvs = [f'../../{algorithm}_serial.csv' for algorithm in algorithms]
parallel_csvs = [f'../../{algorithm}_parallel.csv' for algorithm in algorithms]
tuple_algorithm_list = list(zip(serial_csvs, parallel_csvs))

# Plotting
for serial_csv, parallel_csv in tuple_algorithm_list:
    # Read csv files
    df_serial = pd.read_csv(serial_csv)
    df_parallel = pd.read_csv(parallel_csv)

    # Get mean of times for each array size
    df_serial = df_serial.groupby('Array Size').mean().reset_index()
    df_parallel = df_parallel.groupby(['Array Size', 'Number of Threads']).mean().reset_index()

    # Set execution time to two decimal places
    df_serial['Execution Time (ms)'] = df_serial['Execution Time (ms)'].round(2)
    df_parallel['Execution Time (ms)'] = df_parallel['Execution Time (ms)'].round(2)

    # Get plot title
    title = f"{serial_csv.split('/')[-1].split('_')[0].replace('_', ' ').title()} Sort Serial vs Parallel"

    # Plot for parallel data
    fig = px.line(df_parallel, x='Array Size', y='Execution Time (ms)', color='Number of Threads', title=title, markers=True)

    # Generate the serial line plot and extract the trace
    fig_serial = px.line(df_serial, x='Array Size', y='Execution Time (ms)', title=title, markers=True)
    serial_trace = fig_serial.data[0]  # Extract the trace from the serial plot

    # Add the serial trace to the main plot
    fig.add_trace(serial_trace)

    fig.show()
    
