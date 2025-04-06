import os
import shutil

input_dir = "tms_pbf_tiles"
output_dir = "xyz_tiles"

for z in os.listdir(input_dir):
    z_path = os.path.join(input_dir, z)
    if not os.path.isdir(z_path):
        continue
    for x in os.listdir(z_path):
        x_path = os.path.join(z_path, x)
        if not os.path.isdir(x_path):
            continue
        for y_tms in os.listdir(x_path):
            y_xyz = (2 ** int(z) - 1) - int(y_tms.split('.')[0])  # Flip y
            src = os.path.join(x_path, y_tms)
            dest_dir = os.path.join(output_dir, z, x)
            os.makedirs(dest_dir, exist_ok=True)
            dest = os.path.join(dest_dir, f"{y_xyz}.pbf")
            shutil.copy(src, dest)